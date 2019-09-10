push 0
push 9
push -2
lfp
add
lw
print
push -2
lfp
add
lw
push 5
beq label2
push 0
b label3
label2:
push 1
label3:
push 1
beq label0
b label1
label0:
label1:
halt
