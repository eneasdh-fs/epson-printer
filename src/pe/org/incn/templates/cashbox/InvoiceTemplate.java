package pe.org.incn.templates.cashbox;

import jpos.JposException;
import pe.org.incn.base.EpsonPrintable;
import pe.org.incn.base.support.GroupFormatter;
import pe.org.incn.json.JSONObject;

/**
 * InvoiceTemplate
 *
 * @author enea <enea.so@live.com>
 */
public class InvoiceTemplate extends PaymentDocument {

    public InvoiceTemplate(JSONObject json, EpsonPrintable printer) {
        super(json, printer);
    }

    @Override
    protected String documentName() {
        return "Factura";
    }

    @Override
    protected void writeOwnerAttributes() throws JposException {
        this.getWriter().groupWords("Razón social", json("owner_name").toUpperCase());
        this.getWriter().groupWords("R.U.C.", json("owner_ruc").toUpperCase());
    }

    @Override
    protected void totalsAttributes() throws JposException {
        writer.writeLine(GroupFormatter.instance("IGV", json("definitive_igv")).makeSpaceBetween());
    }
}
