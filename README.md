# Carya Excel
Carya Excel is an easy-to-use, java based, open source reading and writing excel tools.

## 功能说明

### ExcelColumn
> 对象中需要导入/导出的字段，需要加上 ExcelColumn 标注。

### ExcelStrategy & ExcelStrategies
> 默认策略：导入/导出对象中所有标注为 ExcelColumn 的字段。
>
> 自定义策略：导入/导出 ExcelStrategy 标注中列出的字段(标注为 ExcelColumn 的字段)。

1. 对象未加上 ExcelStrategy 或 ExcelStrategies 标注，将采用默认策略；
2. 单种策略，使用 ExcelStrategy 标注；
3. 多种策略，使用 ExcelStrategies 标注。

### ExcelProperty
> needHeader：导入/导出时，是否需要标题行。
>
> i18nHeader：标题行文字是否需要国际化。如果国际化，则title中应该是i18n key。
>
> autoSizeColumn：导出时，是否设置为自适应列宽。

### CellFontFormat
> 定义字体

### CellStyleFormat
> 定义单元格样式

## 使用说明

### 导出

### 导入

## Contributing

Contributors are welcomed to join the Carya Excel project. Please check [CONTRIBUTING](./CONTRIBUTING.md) about how to contribute to this project.


## License

Carya Excel is under the MIT license. See the [LICENSE](./LICENSE) file for details.