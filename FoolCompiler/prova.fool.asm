push 0
cfp
pop
lfp
cfp
push function0
push function1
pop
pop
sfp
halt

function0:
cfp
lra
lfp
cfp
sfp
sra
pop
pop
pop
sfp
lra
js

function1:
cfp
lra
lfp
cfp
lfp
push 1
lfp
lw
add
lw
push 1
lfp
lw
add
lw
lfp
lw
lw
push -1
lfp
lw
lw
add
lw
js
sfp
sra
pop
pop
sfp
lra
js
