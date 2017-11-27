#include <stdio.h>
#include <stdlib.h>

#define BIAS 132

int encode(int sample)
{
    int z = 0;
    int p = 0;
    int m;

    sample += BIAS;
    int shifting_sample = sample << 1;

    while ((0x8000 & shifting_sample) != 0x8000)
    {
        shifting_sample <<= 1;

        p++;
    }
    p = 7-p;
    shifting_sample <<= 1;

    m = (shifting_sample & 0xF000) >> 12;

    return (p << 4) + m;
}

int decode(int code)
{
    int e = (code & 0x70) >> 4;
    int m = (code & 0x0F);

    int A = e + 3;
    int B = (BIAS << e) - BIAS;

    return (m << A) + B;
}

int main()
{
    int code = encode(5014);

    int decoded = decode(code);
    printf("%d %d\n", code, decoded);
    return 0;
}
