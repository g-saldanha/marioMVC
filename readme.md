## NOTAS E ENTREGAS MARIO KART

### Texto com instruções de instalação / manutenção para futuros usuários do software 
para ter o código em sua máquina, é necessário clonar o repositório executando o comando

```git clone https://github.com/g-saldanha/marioMVC.git```

Se for rodado em sua IDE de preferência, seja Eclipse, Intellij ...
basta apenas baixar o seu código e rodar através do Gerenciador, que é a classe principal do projeto.

nota-se que para que seja feito o jogo em turnos com conexão é preciso os outros itens inerentes ao netgames vistos aqui:

[Aprenda a usar o NetGames](https://www.inf.ufsc.br/~ricardo.silva/INE5417e5608/trabalho5.htm)

#### Jar

Caso for rodar diretamente pelos executáveis, é necessario rodar o servidor.jar dentro da pasta servidor e depois abrir o Jar marioMVC.jar


### Explicitação dos aspectos positivos e negativos da versão produzida 
 
 Essa versão segue um padrão de arquitetura chamado MVC
 Arquitetura Modelo-Visão-Controlador (do inglês: Model-View-Controller - MVC) 
 é um padrão de arquitetura de software que separa a representação da informação da interação do usuário com ela. 
 Normalmente usado para o desenvolvimento de interfaces de usuário que divide uma aplicação em três partes interconectadas. 
 Isto é feito para separar representações de informação internas dos modos como a informação é apresentada para e aceita pelo usuário. 
 O padrão de projeto MVC separa estes componentes maiores possibilitando a reutilização de código e desenvolvimento paralelo de maneira eficiente.
 
 
**Camada de apresentação ou visualização (View)** - Não se dedica em saber como o conhecimento foi retirado ou de onde ela foi obtida, apenas mostra a referência. Segundo Gamma et al (2006), ”A abordagem MVC separa a View e Model por meio de um protocolo inserção/notificação (subscribe/notify). Uma View deve garantir que sua expressão reflita o estado do Model. Sempre que os dados do Model mudam, o Model altera as Views que dependem dele. Em resposta, cada View tem a oportunidade de modificar-se”. Adiciona os elementos de exibição ao usuário : HTML, ASP, XML, Applets. É a camada de interface com o usuário. É utilizada para receber a entrada de dados e apresentar visualmente o resultado.

**Camada de lógica da aplicação (Model)** - É o coração da execução, responsável por tudo que a aplicação vai fazer a partir dos comandos da camada de controle em um ou mais elementos de dados, respondendo a perguntas sobre o sua condição e a instruções para mudá-las. O modelo sabe o que o aplicativo quer fazer e é a principal estrutura computacional da arquitetura, pois é ele quem modela o problema que está se tentando resolver. Modela os dados e o comportamento por atrás do processo de negócios. Se preocupa apenas com o armazenamento, manipulação e geração de dados. É um encapsulamento de dados e de comportamento independente da apresentação.

**Camada de controle (Control)** - É responsável por interpretar as ações de entrada através do mouse e teclado realizadas pelo usuário. O Controle (Controller) envia essas ações para o Modelo (Model) e para a janela de visualização (View) onde serão realizadas as operações necessárias.
 
 
 O código também tem suporte para Internacionalização(i18n) de forma simples, visto que todas suas frases de comunicação direta com a interface estão contidas em uma única  classe chamada Constantes, para deixar mais completo, seria necessário que ele tiver um bundler para pegar a localidade do indíviduo que iniciou a aplicação e traduzir automaticamente.
 
 Infelizmente o projeto modelado inicialmente continha uma opção de escolha de personagem ao iniciar a partida, por decisão de complexidade, prazo e custo/benefício não conseguimos implementar diretamente essa funcionalidade, serveria para um trabalho futuro caso alguma outra pessoa desejasse pegar o trabalho.
 
### Eventuais restrições contidas na versão entregue:

O trabalho é feito em swing e java desktop, estas ferramentas estão estão muito descontinuadas/obsoletas no quesito tecnológico.
O código está praticamente todo restrito a modelagem UML, tudo que você ver dentro da modelagem vai estar refletido no código, desde métodos, estados, interações e classes, então não tem nada diferente do que foi proposto incialmente





