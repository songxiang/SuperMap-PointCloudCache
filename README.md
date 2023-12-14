```
PointCloudFileInfo info = new PointCloudFileInfo();
/**
 * 设置点云生成的las文件，这里的las文件需要按照这里设置为准。一个名字下面可以有多个las文件。
 */
HashMap<String, ArrayList<String>> groupMap = new HashMap<>();
ArrayList<String> lasfiles = new ArrayList<>();
lasfiles.add(m_txtLasFile.getText().trim());
groupMap.put("group", lasfiles);
info.setGroupFileNames(groupMap);
/**
 * 设置坐标系
 */
PrjCoordSys prj = PrjCoordSys.fromEPSG(epsg);
info.setSrcPrjCoordSys(prj);
/**
 * 设置点云数据单位，如果坐标系是经纬度那么设置为度。如果坐标系是投影坐标系或者是平面无投影，此时设置为米。如果有其他单位请在此修改代码
 */
if (prj.getType() == PrjCoordSysType.PCS_EARTH_LONGITUDE_LATITUDE) {
    info.setPointDataUnit(DataUnit.Degree);
} else {
    info.setPointDataUnit(DataUnit.Meter);
}
/**
 * 设置点云数据参考点，如果数据没有参考点，那么默认为0，如果有参考点，那么以实际参考点维准
 */
Point3D position = new Point3D(Double.valueOf(m_txtX.getText().trim()), Double.valueOf(m_txtY.getText().trim()), Double.valueOf(m_txtZ.getText().trim()));
info.setPosition(position);

PointCloudCacheBuilder cacheBuilder = new PointCloudCacheBuilder();
/**
 * 设置点云瓦片的名称
 */
cacheBuilder.setCacheName(m_txtLasCacheName.getText().trim());
/**
 * 设置点云文件的配置信息
 */
cacheBuilder.setPointCloudInfos(info);
/**
 * 设置S3M的版本，这里默认以3.0,该模式也是目前最优的选择
 */
cacheBuilder.setCacheVersion(S3MVersion.VERSION_30);
/**
 * 设置瓦片文件类型，这里最优选择是S3MB
 */
cacheBuilder.setFileType(CacheFileType.S3MB);
/**
 * 设置集合压缩类型，此时选择DRACO。该压缩类型是与S3M3.0配套使用，保证后续浏览性能，但是会增加生成瓦片时间
 */
cacheBuilder.setGeometryCompressType(MeshCompressType.DRACO);
/**
 * 设置点云瓦片的输出路径
 */
cacheBuilder.setOutputFolder(m_txtOutputPath.getText().trim());
/**
 * 设置生成瓦片的处理模式，这里最好选择追加模式
 */
cacheBuilder.setProcessType(ProcessFileType.ADD);
/**
* 设置点云数据特征值字段，根据界面选择设置对应的特征值。
* 强度：INTENSITY
* 类别：CLASSIFY，如果选择类别此范例中默认是全部类别，如果要获取类别并设置制定类别，代码如下；
* cacheBuilder。getValidClassifyInfos(string)
* PointCloudFileInfo.setArrClassify([])
* 高度：POSZ
*/
PointCloudCategoryField field = m_cmbType.getSelectedItem().equals("高度") ? PointCloudCategoryField.POSZ : m_cmbType.getSelectedItem().equals("类别") ? PointCloudCategoryField.CLASSIFY : PointCloudCategoryField.INTENSITY;
cacheBuilder.setCategoryField(field);
/**
 * 设置耽搁Tile的镜子塔剖分方式，默认设置为四叉树。如果点云数据的高度相差特别大此时需要用八叉树，如果高度相差不大，此时选择四叉树.
 */
cacheBuilder.setTilePyramidSplitType(PyramidSplitType.QuadTree);

mainPanel.setCursor(new Cursor(3));
if (cacheBuilder.build()) {
    JOptionPane.showMessageDialog(null, "点云数据生成瓦片成功");
} else {
    JOptionPane.showMessageDialog(null, "点云数据生成瓦片失败");
}
maiPanel.setCursor(new Cursor(0));

```
