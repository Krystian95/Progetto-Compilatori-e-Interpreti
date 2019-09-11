push 0
push 1
push function0
lfp
push 54
lfp
push -3
lfp
add
lw
js

function0:
cfp
lra
push 1
lfp
add
lw
push 0
beq label2
push 0
b label3
label2:
push 1
label3:
push 1
beq label0
lfp
push 1
lfp
add
lw
push 1
sub
lfp
lw
lw
lw
push -3
lfp
lw
lw
lw
add
lw
js
b label1
label0:
push -2
lfp
add
lw
print
label1:
sra
pop
pop
sfp
lra
js
halt

function0:
cfp
lra
push 1
lfp
add
lw
push 0
beq label2
push 0
b label3
label2:
push 1
label3:
push 1
beq label0
lfp
push 1
lfp
add
lw
push 1
sub
lfp
lw
lw
lw
push -3
lfp
lw
lw
lw
add
lw
js
b label1
label0:
push -2
lfp
add
lw
print
label1:
sra
pop
pop
sfp
lra
js
