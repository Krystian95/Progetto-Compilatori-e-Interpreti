push 0
cfp
pop
lfp
cfp
push 3
push function0
push 3
lfp
push -1
lfp
add
lw
lfp
push -2
lfp
add
lw
js
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
push -1
lfp
lw
lw
add
lw
push 1
add
lfp
lw
push 1
add
sw

push 1
lfp
lw
add
lw

lfp
lw
lw
lw
push -1
add
sw
push 3
lfp
lw
lw
push -1
add
sw
sfp
sra
pop
pop
sfp
lra
js
