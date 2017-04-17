PostgresQL 字符串隐式转换JSON脚本：

-- 隐式将varchar转换为json
CREATE OR REPLACE FUNCTION json_in_varchar(varchar) RETURNS json AS $$
SELECT json_in($1::cstring); 
$$ LANGUAGE SQL IMMUTABLE;

DROP CAST IF EXISTS (varchar as json);
CREATE CAST (varchar AS json) WITH FUNCTION json_in_varchar(varchar) AS IMPLICIT;

-- 隐式将varchar转换为jsonb
CREATE OR REPLACE FUNCTION jsonb_in_varchar(varchar) RETURNS jsonb AS $$
SELECT jsonb_in($1::cstring); 
$$ LANGUAGE SQL IMMUTABLE;

DROP CAST IF EXISTS (varchar as jsonb);
CREATE CAST (varchar AS jsonb) WITH FUNCTION jsonb_in_varchar(varchar) AS IMPLICIT;


框架(已经使用Spring Security.)：
http://shiro.apache.org/
Apache Shiro™ is a powerful and easy-to-use Java security framework that performs authentication, 
authorization, cryptography, and session management. With Shiro’s easy-to-understand API, 
you can quickly and easily secure any application – from the smallest mobile applications to 
the largest web and enterprise applications. 
