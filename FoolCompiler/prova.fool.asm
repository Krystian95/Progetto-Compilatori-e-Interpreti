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
sfp
b label1
label0:
lfp
cfp
sfp
label1:
pop
pop
sfp
halt
