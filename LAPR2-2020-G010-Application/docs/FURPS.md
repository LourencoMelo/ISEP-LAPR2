# Especificação suplementar

## Funcionalidades

*Especifica as funcionalidades que não se relacionam com os casos de
uso, nomeadamente: Auditoria, Reporte e Segurança.*

**Segurança:**
  
- **As interações dos utilizadores supramencionados (i.e. Adminsitrativo, Freelancer, Gestor, Colaborador) devem ser precedidas de um processo de autenticação.**
- **A utilização da plataforma por outras pessoas é restrita ao registo de organizações.**


## Usabilidade

*Avalia a interface com o utilizador. Possui diversas subcategorias,
entre elas: prevenção de erros; estética e design; ajudas (Help) e
documentação; consistência e padrões.*

**Estética:**

- **Em termos visuais, a interface gráfica da aplicação deve assentar numa paleta de cores estruturada em duas cores (primária e secundária).**

**Ajudas:**

- **Durante a utilização do sistemas os diversos utilizadores devem ter facilmente acesso a informação de apoio/suporte à tarefa que estão a realizar no momento.**

## Fiabilidade/Confiabilidade
*Refere-se a integridade, conformidade e interoperabilidade do software. Os requisitos a serem considerados são: frequência e gravidade de falha, possibilidade de recuperação, possibilidade de previsão, exatidão, tempo médio entre falhas.*

-

## Desempenho
*Avalia os requisitos de desempenho do software, nomeadamente: tempo de resposta, consumo de memória, utilização da CPU, capacidade de carga e disponibilidade da aplicação.*

- **Salienta-se ainda a necessidade do sistema estar preparado para suportar adequamente picos de utilização elevada do sistema, em particular, pelos freelancers.**

## Suportabilidade
*Os requisitos de suportabilidade agrupam várias características, como:
testabilidade, adaptabilidade, manutibilidade, compatibilidade,
configurabilidade, instalabilidade, escalabilidade entre outros.*

**Configurabilidade:**

- **A designação comercial da plataforma e outros dados que venham a ser relevantes devem ser especificados por configuração aquando da sua implantação.**
- **Algoritmo (gerador de palavras-passe) externo configurado apenas aquando da implantação do sistema.**
- **A paleta de cores [...deve] ser configurável aquando da sua implantação.**
- **O precesso de instalação e configuração do sistema deve ser muito simples, intuitivo, rápido e acima de tudo adequado a pessoas com poucos conhecimentos técnicos.**

**Testabilidade:**

- **[...] especificar um conjunto relevante de testes de cobertura e mutação (e.g. unitários, funcionais e de integração) [...]**



## +

### Restrições de design

*Especifica ou restringe o processo de design do sistema. Exemplos podem incluir: linguagens de programação, processo de software, uso de ferramentas de desenvolvimento, biblioteca de classes, etc.*

- **Adotar boas práticas de identificação de requisitos e de análise e design de software OO**

- **Reutilizar o componente de gestão de utilizadores existente na T4J**

- **As palavras-passe [...] devem ser geradas [...] recorrendo a um algoritmo externo (i.e. concebido por terceiros) e configurado apenas aquando da implantação
  do sistema.**
  
- **Pretende-se que a adição de novos tipos de regimento esteja facilitada e, se possível, possa até ser realizada por terceiros.**
 


### Restrições de implementação

*Especifica ou restringe o código ou a construção de um sistema tais
como: padrões obrigatórios, linguagens de implementação, políticas de
integridade de base de dados, limites de recursos, sistema operativo.*

- **Implementar o núcleo principal do software em Java**
 
- **Adotar normas de codificação reconhecidas**

### Restrições de interface

*Especifica ou restringe as funcionalidades inerentes à interação do
sistema com outros sistemas externos.* 

- **As palavras-passe [...] devem ser geradas [...] recorrendo a um algoritmo externo (i.e. concebido por terceiros).**

### Restrições físicas

*Especifica uma limitação ou requisito físico do hardware utilizado, por
exemplo: material, forma, tamanho ou peso.*

- 