push 0
cfp
pop
lfp
cfp
push 9
push function0
lfp
push 5
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
push 2
lfp
lw
add
lw
bgt label2
push 0
b label3
label2:
push 1
label3:
push 1
beq label0
lfp
cfp
push 1
pop
sfp
b label1
label0:
lfp
cfp
push 1
lfp
lw
lw
add
lw
push 2
lfp
lw
lw
add
lw
add
print
pop
sfp
label1:
sfp
sra
pop
pop
pop
sfp
lra
js
