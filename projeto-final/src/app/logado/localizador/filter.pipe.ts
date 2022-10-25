import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(value: any,  filterText: string){
    if(value.length === 0){
                    return value;
                } 

                const Itens = [];
                for(const user of value){
                    if (user['name'] === filterText){
                        Itens.push(user);
                    }
                }
                return Itens;
            }
        }
        

