class CartãoDeCrédito {
    class CartãoDeCrédito {
        private String numero;
        private String nomeTitular;
        private String cpfTitular;
        private double limite;
        private double saldo;
        private double taxaCashback;
    
        public CartãoDeCrédito(String numero, String nomeTitular, String cpfTitular) {
            this(numero, nomeTitular, cpfTitular, 1000.0, 0.0); 
        }
    
        public CartãoDeCrédito(String numero, String nomeTitular, String cpfTitular, double limite, double taxaCashback) {
            this.numero = numero;
            this.nomeTitular = nomeTitular;
            this.cpfTitular = cpfTitular;
            this.limite = limite;
            this.saldo = 0.0; 
            setTaxaCashback(taxaCashback); 
        }
    
        public String getNumero() {
            return numero;
        }
    
        public String getNomeTitular() {
            return nomeTitular;
        }
    
        public String getCpfTitular() {
            return cpfTitular;
        }
    
        public double getLimite() {
            return limite;
        }
    
        public double getSaldo() {
            return saldo;
        }
    
        public double getTaxaCashback() {
            return taxaCashback;
        }
    
        public void setLimite(double limite) {
            this.limite = limite;
        }
    
        public void setSaldo(double saldo) {
            this.saldo = saldo;
        }
    
        public void setTaxaCashback(double taxaCashback) {
            if (taxaCashback < 0 || taxaCashback > 100) {
                throw new IllegalArgumentException("Taxa de cashback deve estar entre 0 e 100.");
            }
            this.taxaCashback = taxaCashback;
        }
    
        public double consultarSaldo() {
            return getSaldo();
        }
    
        public double consultarLimite() {
            return getLimite();
        }
    
        public String realizarTransacao(double valor) {
            return realizarTransacao(valor, false);
        }
    
        public String realizarTransacao(double valor, boolean comCashback) {
            if (valor <= 0) {
                return "Valor inválido para a transação.";
            }
            if (valor > getLimite()) {
                return "Transação negada: valor acima do limite.";
            }
            if (valor > getSaldo()) {
                return "Transação negada: saldo insuficiente.";
            }
    
            double novoSaldo = getSaldo() - valor;
            if (comCashback) {
                double cashback = valor * (taxaCashback / 100);
                novoSaldo += cashback;
                return String.format("Transação realizada com cashback. Novo saldo: %.2f (Cashback: %.2f)", novoSaldo, cashback);
            }
    
            return String.format("Transação realizada. Novo saldo: %.2f", novoSaldo);
        }
    
        public String alterarLimite(double novoLimite) {
            setLimite(novoLimite);
            return String.format("Limite alterado. Novo limite: %.2f", getLimite());
        }
    }
}