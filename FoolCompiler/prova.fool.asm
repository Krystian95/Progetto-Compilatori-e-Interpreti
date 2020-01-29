push 0
cfp
pop
lfp
cfp
push 1
push 2
push 2
bneq label2
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
print
pop
sfp
b label1
label0:
lfp
cfp
push 0
print
pop
sfp
label1:
pop
sfp
halt
