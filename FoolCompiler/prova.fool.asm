push 0
cfp
pop
lfp
cfp
push 8
push 2
push 1
push function0
lfp
push -2
lfp
add
lw
push -1
lfp
add
lw
push -3
lfp
add
lw
lfp
push -4
lfp
add
lw
js
push -1
lfp
add
lw
print
pop
push -2
lfp
add
lw
print
pop
pop
pop
pop
pop
sfp
halt

function0:
cfp
lra
lfp
cfp
sfp
sra
pop
pop
pop
pop
sfp
lra
js
