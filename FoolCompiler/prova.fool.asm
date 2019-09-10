push 0
push 888
push 44
push 55
push 5
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
push 77
push 2
push 6
push -5
lfp
add
lw
print
label1:
push -4
lfp
add
lw
print
halt
