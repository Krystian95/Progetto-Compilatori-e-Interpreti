push 0
cfp
pop
lfp
cfp
push 5
push -1
lfp
add
lw
push -1
lfp
add
lw
push -1
lfp
add
lw
push -2
lfp
add
lw
add
mult
push -3
lfp
add
lw
print
pop
push 1
push 1
beq label0
push 0
b label1
label0:
push 1
label1:
push -4
lfp
add
lw
print
pop
pop
pop
pop
pop
sfp
halt
