# ROT 13 Project #

ROT-13 is a simple substitution cipher where we take a message of only uppercase letters and substitute the letter 13 characters away from its position. So, "A" becomes "N", "B" becomes "O", etc. It wraps so that when you get to "N" it becomes "A", "O" becomes "B" , etc.

You need to do this using computation. It could easily be done using a lookup table but you cannot use that (we have not covered that concept yet).

The message is given as a C-String which is just a bunch of characters which has a zero as the last byte.

In your code I'll need to see the code:

1. Iterate over the C-string.
2. Determine if the character should have ROT-13 applied to it.
2. Code to perform the ROT-13 function
3. Memory which contains shows the ROT-13 transformation of the input string.

You need to demo the code to me in the lab. The assignment itself is turned in automatically by GitHub.

```
SBCC CS IS THE BEST => FOPP PF VF GUR ORFG
```
