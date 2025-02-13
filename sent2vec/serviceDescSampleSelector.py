import re
from random import sample

serviceDescriptions = []
for i in range(5000):
    with open('io-files/alexa_tokenized/corporaMethods_MT_tokenized'.format(i)) as f:
        serviceDescriptions += f.read().split("\n")

for i in range(2,11):
    selectedSample5 = sample(serviceDescriptions, 5)
    selectedSample50 = sample(serviceDescriptions, 50)
    selectedSample500 = sample(serviceDescriptions, 500)
    selectedSample5000 = sample(serviceDescriptions, 5000)
    print("selected service descriptions",i)

    with open('io-files/alexa/iterations/'+str(i)+'/alexa5.txt', 'w') as f:
        f.write('\n'.join(selectedSample5))

    with open('io-files/alexa/iterations/'+str(i)+'/alexa50.txt', 'w') as f:
        f.write('\n'.join(selectedSample50))

    with open('io-files/alexa/iterations/'+str(i)+'/alexa500.txt', 'w') as f:
        f.write('\n'.join(selectedSample500))

    with open('io-files/alexa/iterations/'+str(i)+'/alexa5000.txt', 'w') as f:
        f.write('\n'.join(selectedSample5000))

print("saved service descriptions")