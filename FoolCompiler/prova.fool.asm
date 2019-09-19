push 0
cfp
pop
lfp
cfp
push 3
push function0
push 5
lfp
push -3
lfp
add
lw
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
push -1
lfp
add
lw
print
pop
push -3
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
push 111
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
