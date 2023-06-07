package com.tarea05.myapplication.modelos;

public class Banco {
    Cliente objCliente1, objCliente2, objCliente3, objCliente4, objCliente5;

    public Banco() {
    }

    public Banco(Cliente objCliente1, Cliente objCliente2, Cliente objCliente3, Cliente objCliente4, Cliente objCliente5) {
        this.objCliente1 = objCliente1;
        this.objCliente2 = objCliente2;
        this.objCliente3 = objCliente3;
        this.objCliente4 = objCliente4;
        this.objCliente5 = objCliente5;
    }

    public Cliente getObjCliente1() {
        return objCliente1;
    }

    public void setObjCliente1(Cliente objCliente1) {
        this.objCliente1 = objCliente1;
    }

    public Cliente getObjCliente2() {
        return objCliente2;
    }

    public void setObjCliente2(Cliente objCliente2) {
        this.objCliente2 = objCliente2;
    }

    public Cliente getObjCliente3() {
        return objCliente3;
    }

    public void setObjCliente3(Cliente objCliente3) {
        this.objCliente3 = objCliente3;
    }

    public Cliente getObjCliente4() {
        return objCliente4;
    }

    public void setObjCliente4(Cliente objCliente4) {
        this.objCliente4 = objCliente4;
    }

    public Cliente getObjCliente5() {
        return objCliente5;
    }

    public void setObjCliente5(Cliente objCliente5) {
        this.objCliente5 = objCliente5;
    }

    public Cliente getCliente(int i) {
        Cliente objCliente;
        switch (i) {
            case 0:
                objCliente = this.getObjCliente1();
                break;
            case 1:
                objCliente = this.getObjCliente2();
                break;
            case 2:
                objCliente = this.getObjCliente3();
                break;
            case 3:
                objCliente = this.getObjCliente4();
                break;
            case 4:
                objCliente = this.getObjCliente5();
                break;
            default:
                objCliente = new Cliente();
                break;
        }
        return objCliente;
    }
}
