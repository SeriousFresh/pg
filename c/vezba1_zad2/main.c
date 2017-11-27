#include <stdio.h>
#include <stdlib.h>

int main()
{
    FILE *f;
    char header[44];
    f = fopen("primer.wav", "rb");

    int read_b = read(header, 1, 44, f);

    if(read_b != 44)
    {
        printf("PANIC! cri :'( \n");
    }
    else
    {
        printf("Starting parse...");
    }
    fclose(f);
    return 0;
}
