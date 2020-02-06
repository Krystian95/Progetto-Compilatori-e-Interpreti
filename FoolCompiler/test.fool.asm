push 0
cfp
pop
lfp
cfp
push function0
lfp
push 0
push 0
lfp
push -1
lfp
add
lw
js
push 99
push -2
lfp
add
lw
print
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
push 55
push -1
lfp
add
lw
print
pop
pop
sfp
sra
pop
pop
pop
sfp
lra
js
