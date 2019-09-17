push 0
cfp
pop
lfp
cfp
push 8
push function0
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
sfp
sra
pop
pop
sfp
lra
js
