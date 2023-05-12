package mvc.dataAccess;

import mvc.models.ConnectionFactory;
import mvc.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class ProductDAO extends AbstractDAO<Product>{
    public ProductDAO()
    {

    }

    public int findQuantityById(int id)
    {
        int q = 0;

        return q;
    }
}
