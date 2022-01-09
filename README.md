# tqi_evolution_backend_2021
Desafio TQI - análise de crédito

Linguagem
<img src="https://svgshare.com/i/dPV.svg" width="50px">

FrameWork
<img src="https://svgshare.com/i/dN3.svg" width="35px">

Banco de dados
<img src="https://svgshare.com/i/dNy.svg" width="35px">
______________________________________________________________________________________________________________________________________________________________
<h3>Um pouco sobre a aplicação :technologist:</h3>

A aplicação uma api rest e é separada em camadas, sendo elas: model, repository, service, controller.
onde model é a camada de modelo, onde é definido o nome do objeto e seus atributos. A camada repository é onde fica os métodos de persistência, e que por si já vem com métodos para poder facilitar a persistência. A camada service é a camada onde fica as regras de negócios ou os métodos de serviço, e a camada final que pode ser criado os end-points para fazer as requisições.

<h4>Contexto</h4>
Foi criado uma solução para uma empresa de empréstimos e precisa de um sistema de análise de crédito cuja as funções são: <br>
Cadastro de cliente :pouting_face:<br>
Autenticação :key:<br>
Solicitação de empréstimo :man_dancing:<br>
Acompanhamento das solicitações de empréstimo :iphone:<br> 
Detalhar o emprestimo :mag:
<h5>Excessões :stop_sign:</h5> 

Na parte de cadastro de cliente o email a ser cadastrado não pode já constar no banco de dados. <br>
Na parte de do cadastro do emprestimo, o maximo de quantidade de parcelas é 60, e a data da cobrança da primeira parcela pode ser no maximo até 3 meses
______________________________________________________________________________________________________________________________________________________________
<h4>Ponto chave - Token :key:</4>

A aplicação foi projetado com JWT (java web token)
Esse é um tipo de autenticação onde caso você insira o email e senha que consta de um cliente no banco de dados, é gerado um token (uma sequência de letras, números e pontos) que a própria lógica da apliação verifica que esse token consta na secret da aplicação (uma senha onde guarda todos tokens gerados) e verifica se o token ainda não ta expirado, pois o token tme um tempo de expiração que você define e isso é como se foce uma sessão na aplicação. 
<h5>Previlégios :lock:</h5>
A aplicação foi programada de uma forma que para fazer as requisições é necessário autenticar, e autenticando, todos os registros, buscas e tudo, será apenas do cliente que foi autenticado, você não terá permissão de verficar dados, registros ou registrar dados em outros clientes.

<h5> Esquema </h5>
<img src="https://i.imgur.com/g6rGRNL.jpeg">

______________________________________________________________________________________________________________________________________________________________
<h4> Banco de dados :package:</h4>
O banco utilizado foi mysql, não apenas o conhecimento mas o tempo de experiência, na aplicação existe 3 entidades, sendo elas: cliente, emprestimo, endereco.

<h5> Esquema </h5>
<img src="https://i.imgur.com/oQ90uE1.png">

_____________________________________________________________________________________________________________________________________________________________
<h4> Requisições :traffic_light:</h4>
Todas as requisições é feitas a partir do endereço: | Para agilizar os teste, hospedei a api em um container da minha vps.
Como uma Api é composta por requisições, a aplicação consta com alguns requisições sendo elas: <br>
<img src="https://i.imgur.com/E1xKkiO.png">

Na imagem o número 1 referece a requisição de cadastro, onde você pode mandar um corpo com todos os atributos a ser cadastrados, note: é obrigatorio a inserir os atributos e o valor, pois a aplicação está programada a não aceitar valores vazios, caso você cadastrou, você terá que fazer a requisição do número 2, que se refere a requisição de autenticar na aplicação com o email e senha que o cliente foi criado, após isso é possivel fazer requisições sobre emprestimo no sistema. Como vemos na imagem temos a sessão Autenticação e lá tem a requisição para validar o token, cuja requisição não é utilizado pelo cliente e sim para uma demonstração de como funciona essa função de validação de token.<br>

Vamos para melhor parte, acima falamos sobre o nome das requisições, agora vamos para os end-points.

Cadastrar Cliente - /cadastro-cliente <br>
Autenticar - /login <br>

Listar emprestimos - /emprestimo/todos <br>
Solicitar Emprestimo - /emprestimo/novo <br>
Obter Detalhe de Emprestimo - /emprestimo/detalhe/{{idEmprestimo}} exemplo : /emprestimo/detalhe/11

_____________________________________________________________________________________________________________________________________________________________
<h4>Diagramas de processo BPMN</h4>
Fiz alguns diagramas de processo de cada requisição no modelo BPMN <br>

<h3> Cadastro de cliente </h3>
<img src="https://i.imgur.com/LTkkELX.jpeg">


