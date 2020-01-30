push 0
cfp
pop
lfp
cfp
push 1
push 2
push 1
push 1
beq label0
lfp
cfp
push 6
pop
sfp
b label1
label0:
lfp
cfp
push 5
pop
sfp
label1:
pop
pop
sfp
halt
