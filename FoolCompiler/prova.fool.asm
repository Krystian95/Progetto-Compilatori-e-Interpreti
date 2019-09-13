lfp
cfp
push 1
push function0
lfp
cfp
push 2
lfp
push -1
lfp
add
lw
lfp
lw
push -2
lfp
lw
add
lw
js
pop
lw
sfp
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
lw
sfp
halt

function0:
cfp
lra
lfp
cfp
lw
sfp
sra
pop
pop
sfp
lra
js
