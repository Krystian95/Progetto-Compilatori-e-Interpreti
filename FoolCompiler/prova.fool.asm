push 0
lfp
cfp
push 1
push function0
push function1
push function2
push 666
push 26347
lfp
push -5
lfp
add
lw
lfp
push -3
lfp
add
lw
js
push 6
push 0
push 5
push 999
push -7
lfp
add
lw
print
lfp
lfp
push -2
lfp
add
lw
js
lfp
lfp
push -4
lfp
add
lw
js
halt

function0:
cfp
lfp
cfp
push 88
push -1
lfp
lw
lw
add
lw
print
lra
js

function1:
cfp
lfp
cfp
push 1
lfp
add
lw
print
pop
lra
js

function2:
cfp
lfp
cfp
push 66
print
lra
js
