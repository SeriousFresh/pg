#include <stdio.h>
#include <stdlib.h>

//a is read first
//b is read second
short get_little_endian2(char a, char b)
{
    return a + (b << 8);
}

int main()
{
    FILE *f;
    char header[44];

    f = fopen("primer.wav", "rb");

    int read_b = fread(header, 1, 44, f);

    if (read_b != 44)
    {
        printf("PANIC! cri :'( %d\n", read_b);
    }
    else
    {
        printf("Starting parse...\n");

        //unsigned short format = get_little_endian2(header[20], header[21]);
        char a = (char)0x44;
        char b = (char)0xac;
        short test = get_little_endian2(a, b);
        printf("Format: %u", test);
    }

    fclose(f);
    return 0;
}
