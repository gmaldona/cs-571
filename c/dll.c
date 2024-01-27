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

typedef struct
{
   int elem;              // node element
   struct xdll_node *nxp; // next xor pointer
} xdll_node;

/*
 * Creates a new XDLL given a list size and a max number an element can be.
 * While this function is not part of the requirement, it is provided as a
 * convient function for debugging.
 */
xdll_node *new_xdll(size_t size, int32_t max)
{
   xdll_node *curr = NULL;
   xdll_node *prev = NULL;

   for (size_t i = 0; i < size; ++i)
   {
      int val = rand() % max;

      if (!curr)
      {
         curr = (xdll_node *)malloc(sizeof(xdll_node));
         curr->elem = val;
         continue;
      }

      // assuming curr is already memory allocated...
      xdll_node *next = (xdll_node *)malloc(sizeof(xdll_node));
      next->elem = val;
      curr->nxp = (uintptr_t)prev ^ (uintptr_t)next;
      prev = curr;
      curr = next;
   }
}

void display(xdll_node *node)
{
   printf("%d -> ", node->elem);
}

/*
 * Convience function for free an entire list. Calls out to xdll iterator along
 * along with passing the callback pointer to #free_xdll(struct xdll_node*).
 */
void free_node(xdll_node *node)
{
   free(node);
}

void free(xdll_node *list)
{
   iter(list, free_node);
}

void xor_swap(int *a, int *b)
{
   *a = *a ^ *b;
   *b = *b ^ *a;
   *a = *a ^ *b;
}

/*
 * Iterator function.
 * Internally iterates over the list, and for each node, pass the node into the
 * callback.
 */
void iter(xdll_node *list, void (*iter_fn)(xdll_node *elem))
{
   xdll_node *node_pt = list;
   while (node_pt)
   {
   }
}

/*
 * Rotate the back of the list to the head.
 * Returns the new head.
 */
xdll_node *rotate(xdll_node *head)
{
}

void main(void)
{
   xdll_node *list = new_xdll(10, 100);

   free(list);
   return 0;
}