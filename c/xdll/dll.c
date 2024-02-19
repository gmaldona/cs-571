#include <stdio.h>
#include <stdlib.h>

/*
 * CS 571 Homework 1
 *
 * author: Gregory Maldonado
 * email : gmaldonado@cs.binghamton.edu
 * date  : 2024-01-26
 *
 * Graduate student @ Thomas J. Watson College of Engineering and Applied
 * Sciences, Binghamton University.
 */

#include "dll.h"
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

/*
 * Reference used for taking a numerically value of a pointer and XOR'ing:
 *    https://stackoverflow.com/questions/26569728/using-xor-with-pointers-in-c.
 * function so you don't have to write the double cast everywhere, it's ugly...
 * ptr (pointer) + xor => ptxor
 */
struct xdll_node *ptxor(struct xdll_node *aptr, struct xdll_node *bptr)
{
    return (struct xdll_node *)((uintptr_t)aptr ^ (uintptr_t)bptr);
}

/*
 * Swap two pointers within the XDLL.
 * swap + xor => swxorp
 */
void swxorp(struct xdll_node *aptr, struct xdll_node *bptr)
{
    aptr = ptxor(aptr, bptr);
    bptr = ptxor(bptr, aptr);
    aptr = ptxor(aptr, bptr);
}

void swap(struct xdll_node *aptr, struct xdll_node *bptr)
{
    aptr = (struct xdll_node *)((uintptr_t)aptr ^ (uintptr_t)aptr);
    bptr = (struct xdll_node *)((uintptr_t)bptr ^ (uintptr_t)aptr);
    aptr = (struct xdll_node *)((uintptr_t)aptr ^ (uintptr_t)aptr);
}

/*
 * Creates a new XDLL given a list size and a max number an element can be.
 * While this function is not part of the requirement, it is provided as a
 * convient function for debugging.
 */
struct xdll_node *new_xdll(size_t size, int32_t max)
{
    struct xdll_node *head_pt = NULL;
    struct xdll_node *curr_pt = NULL;
    struct xdll_node *prev_pt = NULL;

    for (size_t i = 0; i < size; ++i)
    {
        int val = rand() % max;

        if (!curr_pt)
        {
            curr_pt = (struct xdll_node *)malloc(sizeof(struct xdll_node));
            curr_pt->elem = val;
            head_pt = curr_pt;
        }
        else
        {
            // assuming curr is already memory allocated...
            struct xdll_node *next_pt = (struct xdll_node *)malloc(sizeof(struct xdll_node));
            next_pt->elem = val;
            next_pt->nxp = ptxor(curr_pt, NULL);
            // https://stackoverflow.com/questions/26569728
            // using-xor-with-pointers-in-c
            curr_pt->nxp = ptxor(prev_pt, next_pt);
            prev_pt = curr_pt;
            curr_pt = next_pt;
        }
    }
    return head_pt;
}

void display(struct xdll_node *node_pt)
{
    printf("%d -> ", node_pt->elem);
}

/*
 * Convince function for freeing nodes.
 */
void free_xdll(struct xdll_node *node_pt)
{
    free(node_pt);
}

/*
 * Iterator function.
 * Internally iterates over the list, and for each node, pass the node into the
 * callback.
 */
void iter(struct xdll_node *list, void (*iter_fn)(struct xdll_node *elem))
{
    struct xdll_node *prev_pt = NULL;
    struct xdll_node *node_pt = list;
    while (node_pt)
    {
        iter_fn(node_pt);

        struct xdll_node *next_pt = (struct xdll_node *)((uintptr_t)prev_pt ^ (uintptr_t)node_pt->nxp);
        prev_pt = node_pt;
        node_pt = next_pt;
    }
}

/*
 * Rotate the back of the list to the head.
 * Returns the new head.
 */
struct xdll_node *rotate(struct xdll_node *head_pt)
{
    struct xdll_node *prev_pt = NULL;
    struct xdll_node *node_pt = head_pt;
    while (node_pt)
    {
        uintptr_t next_pt = ((uintptr_t)prev_pt ^ (uintptr_t)node_pt->nxp);
        prev_pt = node_pt;
        node_pt = (struct xdll_node *)next_pt;
    }
    struct xdll_node *tail_pt = prev_pt;

    struct xdll_node *forward = head_pt;
    struct xdll_node *backwrd = tail_pt;

    struct xdll_node *forward_prev = NULL;
    struct xdll_node *backwrd_prev = NULL;

    while (forward != backwrd)
    {
        struct xdll_node *forward_next_pt = (struct xdll_node *)
                            ((uintptr_t)forward_prev ^ (uintptr_t)forward->nxp);
        struct xdll_node *backwrd_next_pt = (struct xdll_node *)
                            ((uintptr_t)backwrd_prev ^ (uintptr_t)backwrd->nxp);

        forward->elem = forward->elem ^ backwrd->elem;
        backwrd->elem = backwrd->elem ^ forward->elem;
        forward->elem = forward->elem ^ backwrd->elem;

        forward_prev = forward;
        backwrd_prev = backwrd;

        forward = forward_next_pt;
        backwrd = backwrd_next_pt;
    }
    return tail_pt;
}

int main(void)
{
    struct xdll_node *list = new_xdll(10, 100);

    printf("iter(list, display):\n\t");
    iter(list, display);
    printf("\n\n");

    printf("iter(rotate(list), display):\n\t");
    iter(rotate(list), display);
    printf("\n");

    free_xdll(list);

    return 0;
}

/**
 * $ make &&./dll
 * % iter(list, display):
 * %      83 -> 86 -> 77 -> 15 -> 93 -> 35 -> 86 -> 92 -> 49 -> 21 -> 
 * 
 * % iter(rotate(list), display):
 *        21 -> 49 -> 92 -> 86 -> 35 -> 93 -> 15 -> 77 -> 86 -> 83 ->
*/