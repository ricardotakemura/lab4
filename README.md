# Lab 4 - Analise e Design.

## Diferenças entre Classe Abstrata e Interface.

**Classe Abstrata:**
- Pode possuir métodos implementados e abstratos;
- Não pode ser instanciada;
- Herança única;

**Interface:**
- Possui somente métodos abstratos;
- Não pode ser instanciada;
- Herança múltipla;

**Quando usar uma interface?**

Quando as únicas características importantes do objeto abstrato são suas ações (métodos). Um exemplo é a classe “Veiculo” (que é um objeto abstrato que possui um método chamado “andar”).

**Quando usar uma classe abstrata?**

Quando o objeto abstrato alguma caracteristicas (propriedades) e não só ações (método). Um exemplo é a classe “Automovel” (que possue uma propriedade chamada “motor”).

## Quando usar Herança, Composição, Agregação e Relacionamento?

- **Herança:** Usa-se quando a classe tem TODAS as características da classe-pai. Por exemplo: a classe “Carro” que herda de “Automovel”
- **Composição:** Quando algumas características do objeto são usadas e são indispensáveis para a classe. Por exemplo: a classe “Motor” compõe a classe “Carro”, pois sem motor um carro não funciona.
- **Agregação:** Quando algumas características do objeto são usadas, mas não dispensáveis para o funcionamento da objeto que a chamou. Por exemplo: a classe “ArCondicionado” agrega a classe “Carro”, pois um carro sem ar-condicionado ainda funciona, mas este agrega valor.
- **Relacionamento:** Quando uma classe tem um relacionamento com outra, mas nenhuma delas faz parte uma da outra. Por exemplo, a classe Passageiro se relaciona a classe Carro, mas o passageiro não é um componente do carro.

## Erros de design na API do Java.

Erros do uso de herança:
- Classe java.util.Properties;
- Classe java.util.Stack;

## Desacoplando componentes com o uso de interfaces.

O que é mais importante em um aplicativo de software?
- Telas;
- Regras de negócio;
- Aonde as informações são gravadas;

O uso das interfaces para o desacoplamento da “tela” e do “banco de dados”.
