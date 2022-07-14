# desafio-banco-java-dio
## Desafio de projeto “Criando um Banco Digital com Java e Orientação a Objetos” do bootcamp TQI Fullstack Developer da Dio – Digital Innovation One.

O presente trabalho tem por objetivo apresentar o desafio de projeto “Criando um Banco Digital com Java e Orientação a Objetos” do bootcamp TQI Fullstack Developer patrocinado pela Dio – Digital Innovation One.

Importante salientar que o trabalho desenvolvido não utiliza de toda a capacidade e recursos que o paradigma da Programação Orientada a Objetos oferece. Apesar de ser formado em Análise e Desenvolvimento de Sistemas não trabalhei na área, até o momento. Estou em busca dessa oportunidade.

No desafio proposto utilizei do parco conhecimento que possuo sobre o assunto. Tanto que não assistir, até o momento, as vídeo-aulas sobre o paradigma de Orientação a Objetos do bootcamp. Então, é um projeto aberto a reanálise e readequação. E convido a todos para que possa estudá-lo e melhorá-lo. Pretendo com isso aprender mais e melhorar meus conhecimentos sobre o paradigma. Também, estarei realizando novas análises sobre o que foi desenvolvido e melhorando a performance da solução com os conhecimentos adquiridos no curso.

No momento, a solução é linear. Ela não permite algumas funcionalidades que seriam importantes como: Alterar ou excluir informações, fechar uma conta, entre outras. Então segue uma linha bem definida de funcionalidades.

Inicialmente, a solução funciona somente utilizando o console. 

A solução foi desenvolvida utilizando o intellij IDEA community Edition 2022 - Project Structure SDK Java 11.

O desafio tem como domínio/problema criar uma solução que deve oferecer aos clientes dois tipos de conta sendo, conta corrente e poupança, as quais possuem as seguintes funcionalidades: depósito, saque, transferência e impressão de extrato. Todas as operações devem ser na própria instituição. Baseado na descrição do problema, fiz uma análise a parte e desenvolvi uma solução baseado na seguintes diretrizes:

**1)    A primeira diretriz a ser mencionada é que a análise primária da solução levou em consideração cadastrar todas os dados em arquivos .txt. Com isso a solução mantém uma “persistência” de dados.**

>O objetivo era ter sempre uma forma de alimentar as List’s, que foram muitas declaradas, para ter uma base de dados consistente e que o usuário pudesse recuperar as >mesmas durante o uso da aplicação.

>A solução verifica se o arquivo .txt correspondente a função existe, se não cria o arquivo para que as operações possam ser realizadas sem “quebrar” o aplicativo.

>Uma das melhorias a ser implementada e criar um modelo lógico de tabelas, em um Sistema Gerenciador de Banco de Dados(SGBD), e persistir os dados na mesmas.
 

**2)    A segunda diretriz é apresentar uma “interface”, não as da POO, que dá ao usuário uma lista, onde ele pode escolher qual operação deseja realizar.**


 >Nessa diretriz há um situação que deve ser mensurada. A partir do momento em que a solução permite realizar o cadastro de banco, agência e conta é possível analisar, que essas atribuições são de um usuário administrador do sistema ou um gerente. Mas, o sistema é hipotético e deve permitir ao usuário cadastrar essas informações para o correto funcionamento da solução.

>Está em no Main do programa, denominado de menuOperations.

**3)    A terceira diretriz é que possui uma “interface” intermediária para operar as funções do sistema.**

>Nessa diretriz há as classes/métodos que operam as validações e realizam as solicitações determinados na análise da solução.

>Estão no pacote src.com.dio.options.

**4)    A solução cadastra um único banco**
>No início da análise considerava a possibilidade de cadastrar vários bancos, mas, depois de analisar com mais objetividade cheguei à conclusão que estaria desenvolvendo um gerenciador financeiro pessoal. Com algumas modificações é possível desenvolver para esse fim. Porém, há muitas questões a serem identificadas, analisadas e implementadas antes. Então nesse momento, é uma solução para um único banco.

>**4.1 - Atributos obrigatórios:**

>>**4.1.1 - Número do Banco**

>>>Apesar da solução ser hipotética há que se mencionar que todo banco que deseje operar no Brasil deve ser autorizado pelo Banco Central do Brasil e recebe um número de identificação.

>>**4.1.2 - Nome do Banco**

>>>Apesar da solução ser hipotética há que se mencionar que todo banco que deseje operar no Brasil, autorizado pelo Banco Central do Brasil, possui um nome. Mesmo que um nome fantasia.

>**4.2 – Validações**

>>Informar o número e o nome do banco;

**5)    A solução deve cadastrar no mínimo uma agência**

>A análise determinou que não era possível cadastrar um cliente, por mais que ele seja cliente do banco, e criar uma conta sem que haja uma agência, seja física ou digital.

>Nesse caso é obrigatório o cadastro de uma agência.

>**5.1 - Atributos obrigatórios:**

>>**5.1.1 - Número da Agência**

>>>Apesar da solução ser hipotética há que se mencionar que todo banco deve possuir uma agência, seja física ou digital.

>>**5.1.2 - Nome da Agência**

>>>Apesar da solução ser hipotética há que se mencionar que toda agência possui um nome. Apesar do número ser o atributo primário nas operações.

>**5.2 – Validações**

>>Informar o número e o nome da agência;

>>Possuir um banco cadastrado;

>>Não possuir duas agências com o mesmo número;
 
**6)    A solução deve cadastrar, no mínimo, um cliente.**

 >Banco sem cliente(s) é uma instituição falida. Mas, para a solução hipotética é obrigatório o cadastro de, no mínimo, um cliente.
 
>**6.1 - Atributos obrigatórios:**

>>**6.1.1 - Número do CPF**

>>>Todo cliente deve possuir um documento identificador. Para a solução não foi implementado funcionalidades que verificam se o CPF é válido.

>>>Nesse cenário será aceito qualquer valor, desde que seja numérico.

>>**6.1.2 - Nome do Cliente**

>>>O nome do cliente.

>**6.2 – Validações**

>>Informar o CPF e o nome do cliente;

>>Possuir, no mínimo, uma agência cadastrada;

>>Não permitir o cadastro do mesmo cliente, ao validar a informação pelo CPF;
 
**7)    A solução deve cadastrar, no mínimo, duas contas mesmo que do mesmo cliente.**

>A solução, para atender a funcionalidade de transferência, deve cadastrar, no mínimo duas contas, mesmo que para o mesmo cliente.

>Não faz sentido uma funcionalidade de transferência entre contas, principalmente, pertencente a mesma instituição banco que possua somente uma conta.

>**7.1 - Atributos obrigatórios:**

>>**7.1.1 – Cliente cadastrado**
>>>Deve informar o CPF.

>>**7.1.2 – Número da Agência**

>>>A solução mostra as agências cadastradas quando o cliente é validado.

>>>O usuário deve informar o número da agência.

>>**7.1.3 – Tipo da Conta**

>>>Obrigatório informar se a conta é do tipo Corrente ou Poupança.

>>**7.1.4 – Número da Conta**

>>>A solução gera um número aleatório de 5 (cinco) dígitos numéricos. É importante anotar para operar as outras funções.

>**7.2 – Validações**

>>Verifica se tem agência cadastrada.

>>Verifica se tem cliente cadastrado.

>>Verificar se é cliente do Banco, através do CPF.

>>A agência informada está cadastrada;

**8)    A solução deve permitir operações de saque/depósito/transferência**

>São operações básicas oferecidas pelo banco hipotético do domínio/problema do desafio. Mas, há particularidades em cada operação. Mas, antes das especificações vou inverter a ordem de análise apresentada até o momento:

>**8.1 – Validações**

>>**8.1.1 – Para operações de saque e depósitos**

>>>Possuir uma agência e, no mínimo, uma conta cadastrada;

>>>Possuir um cliente cadastrado;

>>>Verificar se a agência/conta de origem existe;

>>**8.1.2 – Para operações de transferências**

>>>Possuir uma agência e, no mínimo, duas contas cadastradas, mesmo que para o mesmo cliente;

>>>Possuir um cliente cadastrado, mesmo que seja titular das duas contas;

>>>Verificar se a agência/conta de origem/destino existem;

>>>Verificar se operação não é para a mesma conta.

 
>>**8.1.3 – Para operações de saques e transferências**

>>>Verificar se há saldo suficiente para a operação.

>>>No caso da operação de transferência, a validação ocorre na conta de origem.
 
>>**8.1.4 – Para operações de saques/depósitos e transferências**

>>>Informar valor acima de zero. Não condiz uma operação que não haja valor, mesmo que seja de 01 (um) centavo.

>>>No cadastro identificar a operação. Exemplo: no caso de um depósito seja identificado com a letra “d”. A solução já cuida disso. Não é atribuição do cliente.

>**8.2 - Atributos obrigatórios:**

>>**8.2.1 – Para operações de saques e depósitos**

>>>**8.2.1.1 – Número da Agência de Origem**

>>>>Número da agência de origem da operação.

>>>**8.2.1.2 – Número da Conta de Origem**

>>>>Número da Conta de Origem da operação.

>>>**8.2.1.3 – Valor da Operação**

>>>>Valor da operação, desde que atenda ao requisito 8.1.4.

>>**8.2.2 – Para operações de transferência**

>>>**8.2.2.1 – As estabelecidas no requisito 8.2.1**

>>>**8.2.2.2 – Número da Agência de destino**

>>>>Número da agência de destino da operação.

>>>**8.2.2.3 – Número da Conta de destino**

>>>>Número da Conta de destino da operação.

>>>**8.2.2.4 – Valor da Operação**

>>>>Valor da operação, desde que atenda ao requisito 8.1.4.

**9 ) Impressão de extrato**

>**9.1 - Atributos obrigatórios:**

>>**9.1.1 – Número da Agência**

>>>O usuário deve informar o número da agência.

>>**9.1.2 – Número da Conta**

>>>O usuário deve informar o número da agência.

>**9.2 – Validações**

>>Verifica se tem agência cadastrada.

>>Verifica se tem cliente cadastrado.

>>Verifica se a agência/conta informada existem.	
