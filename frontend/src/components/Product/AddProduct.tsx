import React, { useState} from 'react';
import { FaBoxOpen } from 'react-icons/fa';

const AddProduct: React.FC = () => {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [images, setImages] = useState<File[]>([]);
  const [isDragging, setIsDragging] = useState(false);

  const handleImageUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files) {
      const newImages = Array.from(event.target.files);
      if (images.length + newImages.length <= 5) {
        setImages([...images, ...newImages]);
      } else {
        alert('You can only upload a maximum of 5 images.');
      }
    }
  };

  const handleDrop = (event: React.DragEvent<HTMLDivElement>) => {
    event.preventDefault();
    setIsDragging(false);
    if (event.dataTransfer.files) {
      const newImages = Array.from(event.dataTransfer.files);
      if (images.length + newImages.length <= 5) {
        setImages([...images, ...newImages]);
      } else {
        alert('You can only upload a maximum of 5 images.');
      }
    }
  };

  const handleDragOver = (event: React.DragEvent<HTMLDivElement>) => {
    event.preventDefault();
    setIsDragging(true);
  };

  const handleDragLeave = () => {
    setIsDragging(false);
  };

  const handleImageRemove = (index: number) => {
    const newImages = images.filter((_, i) => i !== index);
    setImages(newImages);
  };

  const handleSubmit = () => {
    // Add your submit logic here
    console.log({
      name,
      description,
      images,
    });
  };

  return (
    <div className="max-w-2xl mx-auto p-8 bg-white shadow-md rounded-md mt-5 mb-5">
      <div className="flex items-center mb-6">
        <FaBoxOpen className="text-teal-500 text-3xl mr-3" />
        <h2 className="text-3xl font-semibold">Add Product</h2>
      </div>
      <p className="mb-8 text-gray-600">Add a new product to your store.</p>
      <form>
        <div className="mb-8">
          <h3 className="text-xl font-medium mb-3">General</h3>
          <label htmlFor="productName" className="block text-sm font-medium text-gray-700 mb-1">Name*</label>
          <input
            id="productName"
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Give your product a short and clear name."
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-teal-500 focus:border-transparent"
          />
        </div>
        <div className="mb-8">
          <label htmlFor="productDescription" className="block text-sm font-medium text-gray-700 mb-1">Description</label>
          <textarea
            id="productDescription"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            placeholder="Give your product a short and clear description."
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-teal-500 focus:border-transparent"
          />
        </div>
        <div className="mb-8">
          <label htmlFor="productImages" className="block text-sm font-medium text-gray-700 mb-1">Images</label>
          <div
            className={`mt-1 flex justify-center p-6 border-2 border-dashed ${isDragging ? 'border-teal-500' : 'border-gray-300'} rounded-md`}
            onDragOver={handleDragOver}
            onDragLeave={handleDragLeave}
            onDrop={handleDrop}
          >
            <div className="text-center">
              <svg className="mx-auto h-12 w-12 text-gray-400" stroke="currentColor" fill="none" viewBox="0 0 48 48" aria-hidden="true">
                <path d="M8 16v24h32V16H8zm-2-2h36a2 2 0 012 2v28a2 2 0 01-2 2H6a2 2 0 01-2-2V16a2 2 0 012-2z" />
                <path d="M4 8h40v2H4z" />
                <path d="M36 0H12a4 4 0 00-4 4v6h32V4a4 4 0 00-4-4zM8 4a4 4 0 014-4h24a4 4 0 014 4v6H8V4z" />
              </svg>
              <div className="flex text-sm text-gray-600 mt-2">
                <label htmlFor="file-upload" className="relative cursor-pointer bg-white rounded-md font-medium text-teal-600 hover:text-teal-500 focus-within:outline-none focus-within:ring-2 focus-within:ring-offset-2 focus-within:ring-teal-500">
                  <span>Click to browse</span>
                  <input id="file-upload" name="file-upload" type="file" multiple className="sr-only" onChange={handleImageUpload} />
                </label>
                <p className="pl-1">or drag and drop</p>
              </div>
              <p className="text-xs text-gray-500 mt-1">PNG, JPG, GIF up to 10MB</p>
            </div>
          </div>
          {images.length > 0 && (
            <div className="mt-4 flex flex-wrap gap-4">
              {images.map((image, index) => (
                <div key={index} className="relative">
                  <img
                    src={URL.createObjectURL(image)}
                    alt={`Preview ${index}`}
                    className="h-24 w-24 object-cover rounded-md"
                  />
                  <button
                    type="button"
                    onClick={() => handleImageRemove(index)}
                    className="absolute top-0 right-0 bg-red-600 text-white rounded-full p-1 text-xs"
                  >
                    &times;
                  </button>
                </div>
              ))}
            </div>
          )}
        </div>
        <div className="flex space-x-4">
          <button
            type="button"
            onClick={handleSubmit}
            className="bg-teal-500 text-white px-4 py-2 rounded-md hover:bg-teal-600"
          >
            Save product
          </button>
          <button
            type="button"
            className="bg-gray-300 text-gray-700 px-4 py-2 rounded-md hover:bg-gray-400"
          >
            Save as draft
          </button>
        </div>
      </form>
    </div>
  );
};

export default AddProduct;
