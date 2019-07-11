push class0
js
push class1
js
cfp
push 0
push 0
lhp
alloc 0
sdp
push 2
sf
push 1
sf
push 1
sf
lfp
push 10
lfp
push 0
sop
push 0
jdt
print
halt

class0:
push 1
push method0
lra
js

method0:
cfp
lra
push 1
lfp
add
lw
push 2
add
srv
sra
pop
pop
sfp
lrv
lra
js

class1:
push 0
push method1
push method2
lra
js

method1:
cfp
lra
push 1
lfp
add
lw
push 2
sub
srv
sra
pop
pop
sfp
lrv
lra
js

method2:
cfp
lra
lop
ldp
push 3
add
lw
push 1
beq label0
push 0
b label1
label0: 
push 1
lfp
add
lw
push 1
beq label2
push 0
b label1
label2: 
push 1
label1: 
srv
sra
pop
pop
sfp
lrv
lra
js
