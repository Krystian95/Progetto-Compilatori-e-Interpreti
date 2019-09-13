lfp
cfp
push function0
lfp
cfp
push 3
push 4
lfp
push -2
lfp
add
lw
push -1
lfp
add
lw
lfp
lw
push -1
lfp
lw
add
lw
js
pop
pop
lw
sfp
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
push -1
lfp
add
lw
print
pop
push 2
lfp
lw
add
lw
print
pop
push 2
lfp
lw
add
lw
push 2
lfp
lw
add
lw
push -1
lfp
add
lw
add
pop
lw
sfp
sra
pop
pop
pop
sfp
lra
js
