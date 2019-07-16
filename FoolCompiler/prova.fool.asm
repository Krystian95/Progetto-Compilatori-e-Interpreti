push 0
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
push 0
push 5
halt
b label1
label0:
push 0
push 3
push 0
halt
label1:
halt
