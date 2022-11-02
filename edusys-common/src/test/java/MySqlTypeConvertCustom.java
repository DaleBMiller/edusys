import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

class MySqlTypeConvertCustom extends MySqlTypeConvert implements ITypeConvert {

  @Override
  public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
    // 获取字段类型名称并转换为小写
    String t = fieldType.toLowerCase();
    // 判断当前类型是否为tinyint,如果是就设置返回类型为布尔类型
   if (t.contains("tinyint")) {
      return DbColumnType.BOOLEAN;
    }
    // 否则原样返回
    return super.processTypeConvert(globalConfig, fieldType);
  }

}