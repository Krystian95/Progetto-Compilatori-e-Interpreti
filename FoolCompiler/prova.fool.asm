push 0
cfp
pop
lfp
cfp
push function0
push 888
push 9
lfp
push -3
lfp
add
lw
lfp
push -1
lfp
add
lw
js
push -3
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
lfp
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
push -3
add
sw
sfp
sra
pop
pop
sfp
lra
js
