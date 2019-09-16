lfp
cfp
push 8
push function0
push function1
lfp
push -1
lfp
add
lw
lfp
push -3
lfp
add
lw
js
pop
pop
pop
lw
sfp
halt

function0:
cfp
lra
lfp
cfp
push 1
lfp
lw
add
lw
print
pop
lw
sfp
sra
pop
pop
sfp
lra
js

function1:
cfp
lra
lfp
cfp
push 9
push -1
lfp
add
lw
print
pop
lfp
push 1
lfp
lw
lw
push -2
lfp
lw
lw
add
lw
js
pop
lw
sfp
sra
pop
pop
sfp
lra
js
