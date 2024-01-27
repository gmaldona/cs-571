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

#include <stdio.h>
#include <stdlib.h>

struct  xdll_node
{
   int elem;              // node element
   struct xdll_node *nxp; // next xor pointer
} ;

/*
 * Creates a new XDLL given a list size and a max number an element can be.
 * While this function is not part of the requirement, it is provided as a
 * convient function for debugging.
 */
struct xdll_node *new_xdll(size_t size, int32_t max);

/*
 * Swap two pointers within the XDLL.
 * swap + xor => swxorp
 */
void swxorp(struct xdll_node* aptr, struct xdll_node* bptr);

/*
 * Reference used for taking a numerically value of a pointer and XOR'ing:
 *    https://stackoverflow.com/questions/26569728/using-xor-with-pointers-in-c.
 * function so you don't have to write the double cast everywhere, it's ugly...
 * ptr (pointer) + xor => ptxor
 */
struct xdll_node *ptxor(struct xdll_node *aptr, struct xdll_node *bptr);

/*
 * Iterator function.
 * Internally iterates over the list, and for each node, pass the node into the
 * callback.
 */
void iter(struct xdll_node *list, void (*iter_fn)(struct xdll_node *elem));

/*
 * Rotate the back of the list to the head.
 * Returns the new head.
 */
struct xdll_node *rotate(struct xdll_node *head);

/*
 * Utility function for displaying the xdll and tester for #iter(xdll_node*).
 */
void display(struct xdll_node *node);

/*
 * Convience function for freeing nodes.
 */
void free_xdll(struct xdll_node *node);
