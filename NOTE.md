-cascade = CascadeType.ALL:đảm bảo rằng các thao tác CRUD thực hiện trên thực thể Author sẽ được áp dụng tự động trên các thực thể liên kết Book và ngược lại.
Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+"product_img"+File.separator+image.getOriginalFilename());//tao duong dan cho tep hinh anh
Files.copy(image.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
session.setAttribute("successMsg","product saved success");