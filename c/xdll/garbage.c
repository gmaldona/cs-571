#include <stdio.h>
#include <stdlib.h>

int main()
{
   int **ptr1 = (int **)malloc(sizeof(int *));
   if (ptr1 != NULL)
   {
      *ptr1 = (int *)malloc(sizeof(int));
      if (*ptr1 != NULL)
      {
         **ptr1 = 42;
         printf("Value: %d\n", **ptr1);
         int *ptr2 = (int *)malloc(sizeof(int)); // never freed
         free(*ptr1);
         printf("ptr2 Value: %d\n", *ptr2);
         printf("ptr 1 Value: %d\n", **ptr1);
         *ptr1 = ptr2; 
         **ptr1 = 99; 
      }
   }
   return 0; 
}