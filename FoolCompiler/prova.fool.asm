push 0
cfp
pop
lfp
cfp
push 8
push 7
push -1
lfp
add
lw
print
pop
push function0
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
lfp
push -2
lfp
add
lw
lfp
push -3
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
sfp
halt

function0:
cfp
lra
lfp
cfp
push 1
push 1
lfp
lw
add
lw
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
push -2
add
sw
sfp
sra
pop
pop
sfp
lra
js
