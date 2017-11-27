#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <stdlib.h>

/*
   Direct fourier transform
*/
int DFT(int dir,int m,double *x1,double *y1)
{
   long i,k;
   double arg;
   double cosarg,sinarg;
   double *x2=NULL,*y2=NULL;

   x2 = malloc(m*sizeof(double));
   y2 = malloc(m*sizeof(double));
   if (x2 == NULL || y2 == NULL)
      return(0);

   for (i=0;i<m;i++) {
      x2[i] = 0;
      y2[i] = 0;
      arg = - dir * 2.0 * 3.141592654 * (double)i / (double)m;
      for (k=0;k<m;k++) {
         cosarg = cos(k * arg);
         sinarg = sin(k * arg);
         x2[i] += (x1[k] * cosarg - y1[k] * sinarg);
         y2[i] += (x1[k] * sinarg + y1[k] * cosarg);
      }
   }

   /* Copy the data back */
   if (dir == 1) {
      for (i=0;i<m;i++) {
         x1[i] = x2[i] / (double)m;
         y1[i] = y2[i] / (double)m;
      }
   } else {
      for (i=0;i<m;i++) {
         x1[i] = x2[i];
         y1[i] = y2[i];
      }
   }

   free(x2);
   free(y2);
   return(1);
}

int main()
{
    double x1[16];
    double y1[16];
    int i = 0;

    for (i=0; i<16; i++)
    {
        x1[i] = sin(2.0*M_PI*(i/8.0));
        y1[i] = 0;

        //x1[i] += sin(2.0*M_PI*(i/16.0));
        printf("x1[%d]=%lf, y1[%d]=%lf\n", i, x1[i], i, y1[i]);
    }

    DFT(1, 16, x1, y1);

    printf("DFT:\n");

    for (i=0; i<16; i++)
    {
        printf("x1[%d]=%lf, y1[%d]=%lf\n", i, x1[i], i, y1[i]);
    }
    return 0;
}
