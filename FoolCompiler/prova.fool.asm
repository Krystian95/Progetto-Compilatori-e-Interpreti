push 0
cfp
pop
lfp
cfp
push 3
push function0
push 5
lfp
cfp
lfp
cfp
push 3
lfp
push -3
lfp
lw
lw
add
lw
push -1
lfp
lw
lw
add
lw
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
push 7
pop
pop
sfp
sfp
push -1
lfp
add
lw
print
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
push 333
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
push -1
add
sw
sfp
sra
pop
pop
pop
sfp
lra
js
