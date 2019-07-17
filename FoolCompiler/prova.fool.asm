push 0
push 50
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
push 0
push 5
push -2
lfp
add
lw
print
halt
b label1
label0:
push 0
push 30
push 0
push -2
lfp
add
lw
print
halt
label1:
halt
