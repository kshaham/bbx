package demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
public class PersonController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/person")
    public
    @ResponseBody
    Person dailyStats(@RequestParam Integer id) {
        String query = "SELECT first_name, last_name, age" +
                " from person where person.id = " + id;

        return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
            return new Person(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3));
        });

    }
    @RequestMapping(value = "/testdb")
    @ResponseBody
    public int testDb() {
        String query = "SELECT 1+1";
        System.out.println("deee this shit works boyssss");
        return jdbcTemplate
                .queryForObject(query, Integer.class);

    }
    @RequestMapping(value = "/")
    public String home() {
        return "Hello Docker World";
    }


}
