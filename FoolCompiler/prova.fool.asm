push 0
push function0
lfp
push 5
lfp
push -2
lfp
add
lw
js
halt

function0:
cfp
lra
push 0
push 55
push -2
lfp
add
lw
print
halt
sra
pop
pop
sfp
lra
js
