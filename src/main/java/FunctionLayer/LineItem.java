/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 *
 * @author s_ele
 */
public class LineItem
{

    private int idlineitem;
    private String name = "";
    private int length = 0;
    private int qty;
    private String unit;
    private String descriptionUse;
    private int idorder;
    private int idmaterial;
    private int price = 0;

    public LineItem(String unit, String descriptionUse, int idorder, int idmaterial)
    {
        this.unit = unit;
        this.descriptionUse = descriptionUse;
        this.idorder = idorder;
        this.idmaterial = idmaterial;
    }

    public String getName()
    {
        return name;
    }

    public int getIdlineitem()
    {
        return idlineitem;
    }

    public int getLength()
    {
        return length;
    }

    public int getQty()
    {
        return qty;
    }

    public String getUnit()
    {
        return unit;
    }

    public String getDescriptionUse()
    {
        return descriptionUse;
    }

    public int getIdorder()
    {
        return idorder;
    }

    public int getIdmaterial()
    {
        return idmaterial;
    }

    public int getPrice()
    {
        return price;
    }

    public void setIdlineitem(int idlineitem)
    {
        this.idlineitem = idlineitem;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public void setQty(int qty)
    {
        this.qty = qty;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public void setDescriptionUse(String descriptionUse)
    {
        this.descriptionUse = descriptionUse;
    }

    public void setIdorder(int idorder)
    {
        this.idorder = idorder;
    }

    public void setIdmaterial(int idmaterial)
    {
        this.idmaterial = idmaterial;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    @Override
    public String toString()
    {
        return "LineItem{" + "idlineitem=" + idlineitem + ", length=" + length + ", qty=" + qty + ", unit=" + unit + ", descriptionUse=" + descriptionUse + ", idorder=" + idorder + ", idmaterial=" + idmaterial + '}';
    }

}
