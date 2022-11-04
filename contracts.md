# Boas praticas de codigo e git

__git__
- fazer commits, e nomeação de variaveis e funções em ingles

__equipe__
- não fazer upload de codigo com erros, comentado, não identado ou com imports não utilizados
- não alterar código ou funcionalidade que está sob responsabilidade de outro desenvolvedor evitando duplicação de código (utilizando trello, etc)

__codigo__
- não escreva codigo repetido, se precisar usar o mesmo codigo em dois lugares crie uma função e use-a

- padronizar identação utilizando 4 espaços (1-tab)

- padronizar nomeação de classes, variaveis e funções em camelCase
    - ex: carName, printCarName, CarClass

- padronizar nomes de variaveis, classes ou funções que fazem coisas parecidas
    - ex: getPersonName (pegarNomePessoa) e getCarPlate (pegarPlacaCarro)

        _fazendo assim com que se você sabe como obter o nome de uma pessoa você tambem saiba como obter a placa de um carro, já que os dois tem a mesma função de retornar um valor de um objeto_

- criar nomes de funções e variaveis com nomes claros
    - ex: se uma variavel armazena o nome de um carro ele deve se chamar "carName" (nomeCarro), e não batata
    - ex: se uma função mostra o nome de um carro na tela ela deve se chamar "printCarName" (imprimeNomeCarro), e não car
    
        _batata e carro são nomes genéricos que não explicam o que faz a função ou o que armazena a váriavel_
