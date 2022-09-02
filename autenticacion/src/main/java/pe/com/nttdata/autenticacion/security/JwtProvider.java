package pe.com.nttdata.autenticacion.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pe.com.nttdata.autenticacion.dto.RequestDto;
import pe.com.nttdata.autenticacion.model.Usuario;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired //para evitar referencias de  new object
    RouteValidator routeValidator;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }
    //Estructura del token: header(SignatureAlgorithm)+payload(object model)+signature(secret)
    public String createToken(Usuario usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims = Jwts.claims().setSubject(usuario.getUsuario());
        claims.put("id", usuario.getId());
        claims.put("rol", usuario.getRol());
        Date now = new Date();//inicializar el token
        Date exp = new Date(now.getTime() + 3600000);//expira en 1 hora
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    //valida si el token esta disponible y si el usuario admin es el correcto
    public boolean validate(String token, RequestDto dto) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        }catch (Exception e){
            return false;
        }
        //si no es admin(true) y es un path declarado en admin-paths(true)
        if(!isAdmin(token) && routeValidator.isAdminPath(dto))
            return false;//sin autorizacion
        return true;//con autorizacion
    }

    public String getUserNameFromToken(String token){
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        }catch (Exception e) {
            return "bad token";
        }
    }

    private boolean isAdmin(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("rol").equals("admin");
    }
}
