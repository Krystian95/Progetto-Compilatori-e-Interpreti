push 0
cfp
pop
lfp
cfp
push function0
lfp
lfp
push -1
lfp
add
lw
js
lfp
cfp
push function1
lfp
lfp
push -1
lfp
add
lw
js
pop
sfp
lfp
lfp
push -1
lfp
add
lw
js
pop
sfp
halt

function0:
cfp
lra
lfp
cfp
push 7
print
pop
sfp
sra
pop
sfp
lra
js

function1:
cfp
lra
lfp
cfp
push 6
print
pop
sfp
sra
pop
sfp
lra
js
