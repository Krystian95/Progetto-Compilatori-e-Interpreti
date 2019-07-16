push 0
push 4
push 1
push 3
mult
add
push 5
push 2
add
bneq label0
push 0
b label1
label0:
push 1
label1:
halt
